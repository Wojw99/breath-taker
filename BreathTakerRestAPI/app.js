const express = require('express');
const app = express();
const port = 3000; 

const articles = [
    { id: 1, title: 'How to use the app?', text_read: '5 min read', icon_tag : 'help', content : [
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse mi lorem, eleifend et neque dignissim, dignissim condimentum enim.",
        "Duis justo nisl, efficitur id tempor eget, egestas ut est. Phasellus tincidunt lacus eu ipsum maximus, eget cursus justo interdum. Sed nec sodales nunc.",
        "Phasellus porttitor nisl lorem, a bibendum dolor suscipit ac. Donec congue venenatis lorem, ut tincidunt nisl consequat eu. Etiam at venenatis ex.",
    ] }, 
    { id: 2, title: 'How to breath correctly?', text_read: '5 min read', icon_tag : 'breath', content : [
        'Aliquam ligula lacus, bibendum ut nunc vitae, viverra semper libero. Morbi fringilla cursus enim, eget vulputate leo lobortis a. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia curae; Aenean aliquet mauris nec felis semper, sed elementum enim sollicitudin.',
        'Etiam molestie nibh vitae nisl egestas, nec molestie nisl pellentesque. Etiam dictum, purus eu rutrum hendrerit, mi ipsum finibus lectus, eu sollicitudin metus dui dictum turpis.',
        'Sed magna odio.',
        'Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia curae; Vivamus dui justo, vestibulum non enim vel, fringilla lobortis risus. Etiam malesuada diam lorem, at egestas velit ultricies sit amet.',
        'Aliquam ligula lacus, bibendum ut nunc vitae, viverra semper libero. Morbi fringilla cursus enim, eget vulputate leo lobortis a. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia curae; Aenean aliquet mauris nec felis semper, sed elementum enim sollicitudin.',
        'Cyganie w Polsce',
    ] }
]

app.get('/', (req, res) => {
  res.send('Hello BreathTaker API!');
});

app.listen(port, () => {
  console.log(`Server is running on port ${port}`);
});

app.get('/api/articles/main', (req, res) => {
    const articlesWithoutContent = articles.map(({ content, ...rest }) => rest);
    res.json({
        start_button_text: "Take a breath",
        start_button_read_text: "1 to 5 minutes",
        articles_header: "Gain knowledge",
        articles : articlesWithoutContent,
    });
});

app.get('/api/articles/:id', (req, res) => {
    const articleId = parseInt(req.params.id);
    const article = articles.find(article => article.id === articleId);
    if (article) {
        res.json(article);
    } else {
        res.status(404).json({ error: 'Article with given ID not found.' });
    }
});