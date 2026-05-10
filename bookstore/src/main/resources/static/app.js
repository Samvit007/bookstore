/* ======================================================
   app.js — Shared utilities for the Online Bookstore
   ====================================================== */

const API = {
  books:    '/api/books',
  cart:     '/api/cart',
  checkout: '/api/checkout',
  auth:     '/api/auth'
};

/* ---------- Toast Notifications ---------- */
function showToast(message, type = 'info', duration = 3000) {
  let container = document.getElementById('toast-container');
  if (!container) {
    container = document.createElement('div');
    container.id = 'toast-container';
    document.body.appendChild(container);
  }
  const icons = { success: '✅', error: '❌', info: '📚' };
  const toast = document.createElement('div');
  toast.className = `toast ${type}`;
  toast.innerHTML = `<span class="toast-icon">${icons[type] || '📢'}</span><span>${message}</span>`;
  container.appendChild(toast);
  setTimeout(() => {
    toast.classList.add('fade-out');
    setTimeout(() => toast.remove(), 400);
  }, duration);
}

/* ---------- Star Rating HTML ---------- */
function renderStars(rating, max = 5) {
  let html = '';
  for (let i = 1; i <= max; i++) {
    if (rating >= i) html += '★';
    else if (rating >= i - 0.5) html += '½';
    else html += '☆';
  }
  return `<span class="stars">${html}</span>`;
}

/* ---------- Format Currency ---------- */
function formatPrice(price) {
  return `$${Number(price).toFixed(2)}`;
}

/* ---------- Cart Badge Update ---------- */
async function updateCartBadge() {
  try {
    const res = await fetch(`${API.cart}/count`, { credentials: 'include' });
    if (!res.ok) return;
    const data = await res.json();
    const badge = document.getElementById('cart-badge');
    if (badge) {
      badge.textContent = data.count || 0;
      badge.style.display = data.count > 0 ? 'flex' : 'none';
    }
  } catch (_) {}
}

/* ---------- Add to Cart ---------- */
async function addToCart(bookId, title) {
  try {
    const res = await fetch(API.cart, {
      method: 'POST',
      credentials: 'include',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({ bookId, quantity: 1 })
    });
    const data = await res.json();
    if (res.ok) {
      showToast(`"${title}" added to cart! 🛒`, 'success');
      const badge = document.getElementById('cart-badge');
      if (badge) {
        badge.textContent = data.cartCount || 0;
        badge.style.display = 'flex';
      }
    } else {
      showToast(data.error || 'Could not add to cart', 'error');
    }
  } catch (e) {
    showToast('Network error. Please try again.', 'error');
  }
}

/* ---------- Navbar Auth State ---------- */
async function initNavbar() {
  try {
    const res = await fetch(`${API.auth}/me`, { credentials: 'include' });
    const data = await res.json();
    const navRight = document.getElementById('nav-right');
    if (!navRight) return;
    if (data.loggedIn) {
      const initial = (data.fullName || data.username || 'U')[0].toUpperCase();
      navRight.innerHTML = `
        <div class="nav-user-info">
          <div class="user-avatar">${initial}</div>
          <span>${data.fullName || data.username}</span>
        </div>
        <button class="nav-auth-btn" onclick="logout()" id="logout-btn">Logout</button>
        <a href="cart.html" class="nav-cart-btn" id="nav-cart-link">
          🛒 <span>Cart</span>
          <span class="cart-badge" id="cart-badge" style="display:none">0</span>
        </a>`;
    } else {
      navRight.innerHTML = `
        <a href="auth.html" class="nav-auth-btn" id="nav-login-btn">Login / Sign Up</a>
        <a href="cart.html" class="nav-cart-btn" id="nav-cart-link">
          🛒 <span>Cart</span>
          <span class="cart-badge" id="cart-badge" style="display:none">0</span>
        </a>`;
    }
    updateCartBadge();
  } catch (_) {
    updateCartBadge();
  }
}

async function logout() {
  try {
    await fetch(`${API.auth}/logout`, { method: 'POST', credentials: 'include' });
    showToast('Logged out successfully', 'info');
    setTimeout(() => window.location.reload(), 800);
  } catch (_) {}
}

/* ---------- Nav active link ---------- */
function setActiveNavLink() {
  const path = window.location.pathname;
  document.querySelectorAll('.nav-links a').forEach(a => {
    a.classList.remove('active');
    const href = a.getAttribute('href') || '';
    if (path === '/' && href === 'index.html') a.classList.add('active');
    else if (path.includes(href) && href !== 'index.html') a.classList.add('active');
  });
}

/* ---------- Init on DOM ready ---------- */
document.addEventListener('DOMContentLoaded', () => {
  initNavbar();
  setActiveNavLink();
});
