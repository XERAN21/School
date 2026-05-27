// ナイトモード切り替え機能
function toggleNightMode() {
    document.body.classList.toggle('night-mode');

    // ローカルストレージに保存
    if (document.body.classList.contains('night-mode')) {
        localStorage.setItem('nightMode', 'enabled');
        document.querySelector('.night-mode-toggle').textContent = '☀️';
    } else {
        localStorage.setItem('nightMode', 'disabled');
        document.querySelector('.night-mode-toggle').textContent = '🌙';
    }
}

// ページ読み込み時にナイトモードの状態を復元
document.addEventListener('DOMContentLoaded', function() {
    const toggleButton = document.querySelector('.night-mode-toggle');
    
    if (localStorage.getItem('nightMode') === 'enabled') {
        document.body.classList.add('night-mode');
        toggleButton.textContent = '☀️';
    }
    
    // イベントリスナーを追加
    toggleButton.addEventListener('click', toggleNightMode);
});