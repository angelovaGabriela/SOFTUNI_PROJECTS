const movies = [
    {
        id: 1,
        title: 'Jungle Cuise',
        imageUrl: '/img/jungle-cruise.jpeg',
        genre: 'Adventure',
        description: 'Dreaming about saving countless lives and having another adventure, the feisty English feminist and doctor of botany, Dr Lily Houghton'
    },
     {
        id: 2,
        title: 'The Little Mermaid',
        imageUrl: '/img/the-little-mermaid.jpg',
        genre: 'Fantasy',
        description: 'The youngest of King Triton\'s daughters, Ariel is a beautiful and spirited young mermaid'
    },
     {
        id: 3,
        title: 'Home Alone',
        imageUrl: '/img/home-alone.jpeg',
        genre: 'Comedy',
        description: 'It is Christmas time and the McCallister family is preparing for a vacation in Paris'
    }
];

const getAll = () => movies;

export default {
    getAll,
}