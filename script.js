let cart = [];

function updateCartUI() {
    const list = document.getElementById('cart-items');
    const footer = document.getElementById('cart-footer');
    const count = document.getElementById('cart-count');
    const totalDisp = document.getElementById('cart-total');
    
    list.innerHTML = '';
    let total = 0;
    let totalItems = 0;

    if (cart.length === 0) {
        list.innerHTML = '<li style="text-align:center; color:#999; padding:40px;">Your cart is empty.</li>';
        footer.style.display = 'none';
        count.innerText = '0';
        return;
    }

    cart.forEach((item, index) => {
        total += item.price * item.qty;
        totalItems += item.qty;
        list.innerHTML += `
            <li style="display:flex; justify-content:space-between; align-items:center; padding:15px 0; border-bottom:1px solid #f0f0f0;">
                <div><b>${item.name}</b><br><span style="color:#e91e63">₹${item.price}</span></div>
                <div style="display:flex; align-items:center; gap:10px;">
                    <button onclick="changeQty(${index}, -1)" style="border:1px solid #ddd; padding:5px 10px; cursor:pointer">-</button>
                    <b>${item.qty}</b>
                    <button onclick="changeQty(${index}, 1)" style="border:1px solid #ddd; padding:5px 10px; cursor:pointer">+</button>
                </div>
            </li>
        `;
    });
    totalDisp.innerText = '₹' + total;
    count.innerText = totalItems;
    footer.style.display = 'block';
}

function changeQty(index, amt) {
    cart[index].qty += amt;
    if (cart[index].qty <= 0) cart.splice(index, 1);
    updateCartUI();
}

function clearCart() {
    cart = [];
    updateCartUI();
}

document.querySelectorAll('.add-cart-btn').forEach(btn => {
    btn.addEventListener('click', () => {
        const card = btn.closest('.food-card');
        const name = card.dataset.name;
        const price = parseInt(card.dataset.price);

        const existing = cart.find(i => i.name === name);
        if (existing) existing.qty++;
        else cart.push({ name, price, qty: 1 });

        btn.innerText = "Added ✓";
        btn.style.background = "#4caf50";
        setTimeout(() => { btn.innerText = "Add to Cart"; btn.style.background = "#222"; }, 800);
        updateCartUI();
    });
});

function toggleFields() {
    const type = document.getElementById('order-type').value;
    document.getElementById('table-input').style.display = type === 'dinein' ? 'block' : 'none';
    document.getElementById('address-input').style.display = type === 'delivery' ? 'block' : 'none';
}

document.getElementById('order-form').addEventListener('submit', (e) => {
    e.preventDefault();
    if (cart.length === 0) return alert("Cart is empty!");
    
    const paymentChoice = document.getElementById('payment-method').value;
    document.getElementById('modal-customer').innerText = document.getElementById('order-name').value;
    document.getElementById('modal-payment-type').innerText = paymentChoice;
    
    let html = '';
    let total = 0;
    cart.forEach(i => {
        html += `<div style="display:flex; justify-content:space-between;"><span>${i.name} x${i.qty}</span><b>₹${i.price * i.qty}</b></div>`;
        total += i.price * i.qty;
    });
    document.getElementById('modal-summary').innerHTML = html + `<hr><b>Total: ₹${total}</b>`;
    document.getElementById('order-modal').classList.add('show');
});

function updateSentiment(stars) {
    const msg = document.getElementById('sentiment-msg');
    const labels = ["Terrible", "Mediocre", "Average", "Tasty!", "Exquisite!"];
    const colors = ["#f44336", "#ff9800", "#ffeb3b", "#8bc34a", "#4caf50"];
    msg.innerText = labels[stars-1];
    msg.style.color = colors[stars-1];
}

document.getElementById('review-form').addEventListener('submit', (e) => {
    e.preventDefault(); 
    const container = document.getElementById('review-container');
    container.innerHTML = `
        <div class="success-msg-box">
            <div style="font-size: 50px;">🌟</div>
            <h3 style="color:#e91e63; margin:10px 0;">Thank You!</h3>
            <p style="color:#666;">We appreciate your feedback.</p>
            <button class="submit-btn" onclick="location.reload()" style="margin-top:20px; width:auto; padding:10px 30px;">Return</button>
        </div>
    `;
});