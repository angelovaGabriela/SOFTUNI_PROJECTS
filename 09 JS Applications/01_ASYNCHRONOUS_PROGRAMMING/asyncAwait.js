function wait() {
    return new Promise(resolve => {
        setTimeout(resolve, 2000, 'resolved');
    });
}

 async function start() {
    console.log('before promise');

   const value = await wait()//.then(value => console.log(value));
    console.log(value);
    console.log('after promise');
}


start();