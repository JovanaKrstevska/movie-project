import axios from "../custom-axios/axios";

const LibraryService = {
    fetchBooks: (object) => {
        return axios.post("/movies", object);
    },
    addMovie: (title, description, genre, year) => {
        return axios.post("movies/create", {
            "title" : title,
            "description" : description,
            "genre" : genre,
            "year" : year,
        });
    },
    reviewMovie: (id, review) => {
        return axios.post(`/movies/${id}/review`, {
            "review" : review,
        });
    },
    getMovie: (id) => {
        return axios.get(`/movies/${id}`);
    },
    addReview: (id) => {
        return axios.delete(`/movies/${id}/reviews/create`);
    }

}

export default LibraryService;