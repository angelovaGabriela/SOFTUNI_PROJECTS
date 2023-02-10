

function solution() {
    let text = '';
    return operations = {
        append: function(string) {
            text += string;
        },
        removeStart: function(n) {
           text = text.slice(n);
        },
        removeEnd: function(n) {
            text = text.substring(0, text.length - n);
        },
        print: function() {
            console.log(text);
        }

    };
}

// let firstZeroTest = solution(); 

 

// firstZeroTest.append('hello'); 

// firstZeroTest.append('again'); 

// firstZeroTest.removeStart(3); 

// firstZeroTest.removeEnd(4); 

// firstZeroTest.print(); 

 let secondZeroTest = solution(); 

 

secondZeroTest.append('123'); 

secondZeroTest.append('45'); 

secondZeroTest.removeStart(2); 

secondZeroTest.removeEnd(1); 

secondZeroTest.print(); 