const express = require('express');
const app = express();
const port = 3000; 

const articles = [
    { id: 1, title: 'How to use the app?', text_read: '5 min read', icon_tag : 'help', content : [
        ".",
        ".",
        ".",
    ] }, 
    { id: 2, title: 'How to breath correctly?', text_read: '5 min read', icon_tag : 'breath', content : [
        'Take breath using your nose. Sometimes breathing through the mouth is necessary, but generally breathing through your nose helps in preventing infections and face deformations.',
        'Use your belly instead of your chest. When you breathe in, your belly should expand, not your chest. This is the correct way to breathe. Use your hand to feel your belly expanding.',
        'Practice proper breathing. You can practice proper breathing by lying down and placing a book on your belly. When you breathe in, the book should rise. When you breathe out, the book should fall.'
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