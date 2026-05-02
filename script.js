const foods = [
  { id: "f1", name: "Veg Thali", price: 120 },
  { id: "f2", name: "Paneer Curry Meal", price: 150 },
  { id: "f3", name: "South Indian Combo", price: 130 },
  { id: "f4", name: "Healthy Millet Bowl", price: 160 },
];

const subscriptions = [
  { id: "s1", name: "15 Days Plan", price: 1800 },
  { id: "s2", name: "30 Days Plan", price: 3400 },
];

const cart = [];

const foodList = document.getElementById("food-list");
const subscriptionList = document.getElementById("subscription-list");
const cartItems = document.getElementById("cart-items");
const cartTotal = document.getElementById("cart-total");
const placeOrderBtn = document.getElementById("place-order-btn");
const orderStatus = document.getElementById("order-status");

function renderCatalog(items, container) {
  container.innerHTML = "";

  items.forEach((item) => {
    const card = document.createElement("article");
    card.className = "card";

    const title = document.createElement("h3");
    title.textContent = item.name;

    const price = document.createElement("p");
    price.textContent = `Price: ₹${item.price}`;

    const button = document.createElement("button");
    button.textContent = "Add to Cart";
    button.addEventListener("click", () => addToCart(item));

    card.append(title, price, button);
    container.appendChild(card);
  });
}

function addToCart(item) {
  cart.push(item);
  orderStatus.textContent = "";
  renderCart();
}

function renderCart() {
  cartItems.innerHTML = "";

  if (cart.length === 0) {
    const emptyItem = document.createElement("li");
    emptyItem.textContent = "Your cart is empty.";
    cartItems.appendChild(emptyItem);
    cartTotal.textContent = "Total: ₹0";
    return;
  }

  let total = 0;
  cart.forEach((item) => {
    total += item.price;
    const row = document.createElement("li");
    row.textContent = `${item.name} - ₹${item.price}`;
    cartItems.appendChild(row);
  });

  cartTotal.textContent = `Total: ₹${total}`;
}

placeOrderBtn.addEventListener("click", () => {
  if (cart.length === 0) {
    orderStatus.textContent = "Please add at least one item before placing an order.";
    return;
  }

  orderStatus.textContent = "Order placed successfully! Thank you.";
  cart.length = 0;
  renderCart();
});

renderCatalog(foods, foodList);
renderCatalog(subscriptions, subscriptionList);
renderCart();
