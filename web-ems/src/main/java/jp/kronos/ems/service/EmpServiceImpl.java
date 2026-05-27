package jp.kronos.ems.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.kronos.ems.domain.Emp;
import jp.kronos.ems.domain.EmpDetail;
import jp.kronos.ems.form.EmpForm;
import jp.kronos.ems.repository.EmpDetailRepository;
import jp.kronos.ems.repository.EmpRepository;

/**
 * 従業員情報に関する業務ロジックを実装するサービス実装クラスです。
 */
@Service
public class EmpServiceImpl implements EmpService {

	private final EmpRepository empRepository;
	private final EmpDetailRepository empDetailRepository;

	/**
	 * コンストラクタ。
	 * @param repository 従業員リポジトリ
	 * @param empDetailRepository 従業員詳細リポジトリ
	 */
	public EmpServiceImpl(EmpRepository repository, EmpDetailRepository empDetailRepository) {
		this.empRepository = repository;
		this.empDetailRepository = empDetailRepository;
	}

	/**
	 * 指定された部署IDに所属する従業員一覧を取得します。
	 * @param deptId 部署ID
	 * @return 従業員ドメインオブジェクトのリスト
	 */
	@Override
	public List<Emp> getEmpsByDeptId(int deptId) {
		return empRepository.findByDeptId(deptId);
	}

	/**
	 * 指定されたIDの従業員を取得します。
	 * @param id 従業員ID
	 * @return 従業員ドメインオブジェクト
	 */
	@Override
	public Emp getEmp(int id) {
		return empRepository.findById(id);
	}

	/**
	 * 従業員を新規登録します。
	 * 社員番号の重複チェックを行い、従業員情報と詳細情報を同時に登録します。
	 * @param form 登録する従業員情報を保持するフォームオブジェクト
	 * @throws RuntimeException 社員番号が既に登録されている場合
	 */
	@Override
	@Transactional
	public void saveEmp(EmpForm form) {
		int count = empRepository.countByEnumber(form.getEnumber());

		if (count > 1) {
			throw new RuntimeException("この社員番号は既に登録されてます。");
		} else {
			Emp emp = new Emp();
			emp.setEnumber(form.getEnumber());
			emp.setEname(form.getEname());
			emp.setHireDate(form.getHireDate());
			emp.setDeptId(form.getDeptId());
			emp.setTelNumber(form.getTelNumber());
			emp.setEmail(form.getEmail());
			emp.setAddress(form.getAddress());

			empRepository.save(emp);

			EmpDetail detail = new EmpDetail();
			detail.setEmpId(emp.getId());
			detail.setTelNumber(emp.getTelNumber());
			detail.setEmail(emp.getEmail());
			detail.setAddress(emp.getAddress());

			empDetailRepository.save(detail);
		}
	}

	/**
	 * 従業員情報を更新します。
	 * 社員番号の重複チェック（自身を除く）を行い、従業員情報と詳細情報を同時に更新します。
	 * @param id 更新対象の従業員ID
	 * @param form 更新する従業員情報を保持するフォームオブジェクト
	 * @throws RuntimeException 社員番号が他の従業員で既に使用されている場合
	 */
	@Override
	@Transactional
	public void updateEmp(int id, EmpForm form) {
		int count = empRepository.countByEnumberExceptSelf(form.getEnumber(), id);

		if (count >= 1) {
			throw new RuntimeException("この社員番号は既に他の従業員に使用されています。");
		} else {
			Emp emp = new Emp();
			emp.setId(id);
			emp.setEnumber(form.getEnumber());
			emp.setEname(form.getEname());
			emp.setHireDate(form.getHireDate());
			emp.setDeptId(form.getDeptId());
			emp.setTelNumber(form.getTelNumber());
			emp.setEmail(form.getEmail());
			emp.setAddress(form.getAddress());

			empRepository.update(emp);

			EmpDetail detail = new EmpDetail();
			detail.setEmpId(emp.getId());
			detail.setTelNumber(form.getTelNumber());
			detail.setEmail(form.getEmail());
			detail.setAddress(form.getAddress());

			empDetailRepository.update(detail);
		}
	}

	/**
	 * 指定されたIDの従業員を削除します。
	 * 従業員詳細情報を先に削除し、その後従業員情報を削除します。
	 * @param id 削除する従業員ID
	 */
	@Override
	@Transactional
	public void deleteEmp(int id) {
		empDetailRepository.deleteByEmpId(id);
		empRepository.delete(id);
	}
}