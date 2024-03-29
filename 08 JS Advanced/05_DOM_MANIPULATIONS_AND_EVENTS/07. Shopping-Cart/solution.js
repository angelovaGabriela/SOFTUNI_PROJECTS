function solve() {
   let addButtons = document.getElementsByClassName("add-product");
   let textarea = document.getElementsByTagName("textarea")[0];
   Array.from(addButtons).forEach(button => button.addEventListener("click", addProduct));

   let totalMoney = 0;
   let shoppingList = new Set();
   function addProduct (event) {
      
      let currentProduct = event.target.parentElement.parentElement; 
      let collection = Array.from(currentProduct.children);
      let productName = collection[1].firstElementChild.innerHTML;
      let price = collection[3].innerHTML;

      shoppingList.add(productName);
      textarea.value += `Added ${productName} for ${price} to the cart.\n`

      totalMoney += parseFloat(price);
      
   }

   let checkoutButton = document.getElementsByClassName("checkout")[0];
   checkoutButton.addEventListener("click", checkout) 

  function checkout () {

  let arrayOfProducts = Array.from(shoppingList);

  textarea.value += `You bought ${arrayOfProducts.join(", ")} for ${totalMoney.toFixed(2)}.`

  let buttons = Array.from(document.getElementsByTagName("button"));
  buttons.map(b => b.disabled = 'true');
  }
}