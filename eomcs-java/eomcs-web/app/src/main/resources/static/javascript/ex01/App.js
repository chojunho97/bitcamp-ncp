console.log("Hello!")

const express = require('express')
const app = express()
const port = 3000

/*const delay = async () => {
      console.log("===> 잠들다!");
      await sleep(5000);
      console.log("===> 깨어나다!");
}
const sleep = (ms) => {
   return new Promise(resolve=>{
       setTimeout(resolve,ms)
   })
}
*/

app.get('/hello', async(req, res) => {
   await new Promise(resolve => setTimeout(resolve, 5000));
  console.log("Hello!");
  res.send('Hello!');
});

app.get('/exam05_1', async (req, res) => {
  //res.header("Access-Control-Allow-Origin", "*");
  await new Promise(resolve =>  setTimeout(resolve, 10000));
  res.send('console.log("exam05_1 ok");')
})
app.get('/exam05_2', (req, res) => {
  //res.header("Access-Control-Allow-Origin", "*");  
  res.send('console.log("exam05_2 ok");')
})
app.get('/exam05_x', async (req, res) => {
  //res.header("Access-Control-Allow-Origin", "*");
  await new Promise(resolve => setTimeout(resolve, 10000));
  res.send('var rate = 30000;')
})

app.listen(port, () => {
  console.log(`Example app listening on port ${port}`)
})

