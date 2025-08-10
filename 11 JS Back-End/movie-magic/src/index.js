import routes from './routes.js';
import handlebarsInit from './config/handlebars-init.js';
import expressInit from './config/express-init.js';

const app = express();

handlebarsInit(app);
expressInit(app);

app.use(routes); 

app.get('/', (req, res) => {
    res.render('home');
});

app.listen(5000, () => console.log('Server is listening on http://localhost:5000...'))