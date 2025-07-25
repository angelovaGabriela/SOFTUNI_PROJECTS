import express from 'express';
import handlebars from 'express-handlebars';
import routes from './routes.js';

const app = express();

app.engine('hbs', handlebars.engine({
    extname: 'hbs',
}));
app.set('view engine', 'hbs');
app.set('views', './src/views');

app.use(express.urlencoded({extended: false}));
app.use(express.static('public'));

app.use(routes); 

app.get('/', (req, res) => {
    res.render('home');
});

app.listen(5000, () => console.log('Server is listening on http://localhost:5000...'))