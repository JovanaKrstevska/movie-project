import React, {useEffect, useState} from "react";
import {Link, useParams} from 'react-router-dom';
import axios from "axios";


const MoviePage = () => {
    const { id } = useParams();
    const [movie, setMovie] = useState(null);

    useEffect(() => {
        axios.get(`http://localhost:8080/movies/${id}`)
            .then(response => {
                setMovie(response.data);
            })
            .catch(error => {
                console.error('Error fetching movie:', error);
            });
    }, [id]);

    return (
        <div>
            {
            movie &&
                <div>
                    <h2>{movie.title}</h2>
                    <p>{movie.description}</p>
                    <p>Genre: {movie.genre}</p>
                    <p>Release Year: {movie.year}</p>
                </div>
            }
        </div>
    );
};

export default MoviePage;