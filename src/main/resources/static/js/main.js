document.addEventListener('DOMContentLoaded', () => {
  // Load reusable parts dynamically
  fetch('header.html')
    .then(r => r.text())
    .then(html => document.getElementById('header').innerHTML = html);

  fetch('sidebar.html')
    .then(r => r.text())
    .then(html => document.getElementById('sidebar').innerHTML = html);

  fetch('footer.html')
    .then(r => r.text())
    .then(html => document.getElementById('footer').innerHTML = html);
});
