import './App.css';
import React, {Component} from "react";
import { BrowserRouter as Router, Route } from 'react-router-dom';

import Movies from "../Movies/MovieList/movies";
import MovieAdd from "../Movies/MoviesAdd/movesAdd";
import LibraryService from "../../repository/libraryRepository";
import Header from "../Header/header";
import MoviePage from "../Movies/MoviePage/moviePage";
import ReviewAdd from "../Movies/ReviewAdd/reviewAdd";

class App extends Component{

    constructor(props) {
        super(props);
        this.state = {
            movies: [],
            categories: [],
            authors: [],
            selectedBook: {},
            selectedMovie: {},
            object: {
                title: "",
                // genres: [],
                // yearFrom: "2000",
                // yearTo: "2020",
                // averageRating: "5.0"
            }
        }
    }

    render() {
        return (

                <Router>
                    <Header/>
                    <main>
                        <div className="container">

                            <Route path={"/movies/create"} exact render={() =>
                                <MovieAdd categories={this.state.categories}
                                            authors={this.state.authors}
                                            onAddMovie={this.addMovie}/>}/>

                            <Route path={"/movies/:id/reviews/create"} exact render={() =>
                                <ReviewAdd  onAddReview={this.reviewMovie}
                                    movie={this.state.selectedMovie}/>}/>

                            <Route path={"/movies/:id"} exact render={() =>
                                <MoviePage categories={this.state.categories}
                                          authors={this.state.authors}
                                          onAddMovie={this.addMovie}/>}/>


                            <Route path={"/movies"} exact render={() =>
                                <Movies movies={this.state.movies}
                                        onDelete={this.deleteBook}
                                        onEdit={this.getMovie}
                                        onReview={this.getMovie}/>}/>

                        </div>
                    </main>

                </Router>

        );
    }

    componentDidMount() {
        this.loadMovies();
    }



    loadMovies = () =>{
        LibraryService.fetchBooks(this.state.object)
            .then((data)=> {
                console.log(this.state.object);
                this.setState({
                    movies: data.data

                })
                console.log(this.state.movies);
            });
    }

    addMovie = (title, description, genre, year) => {
        LibraryService.addMovie(title, description, genre, year)
            .then(() => {
                this.loadMovies();
            });
    }
    getMovie = (id) => {
        LibraryService.getMovie(id)
            .then((data) => {
                this.setState({
                    selectedMovie: data.data
                })
            })
    }


    reviewMovie = (id, review) => {
        LibraryService.reviewMovie(id, review)
            .then(() => {
                this.loadMovies();
            });
    }
    addReview = (review) => {
        LibraryService.addReview(review)
            .then(() => {
                this.loadMovies();
            });
    }


}

export default App;
