function search() {
   let getTowns = Array.from(document.querySelectorAll('ul li'));
   let getInput = document.getElementById('searchText').value;
   let matches = 0;


   for (let city of getTowns) {
      city.style.textDecoration = null;
      city.style, fontWeigth = null;
   }

   for (let town of getTowns) {

      if (town.textContent.includes(getInput)) {
         town.style.textDecoration = 'underline';
         town.style.fontWeight = 'bold';
         matches++;


      }


   }

   document.getElementById('result').textContent = `${matches} matches found`;
}
