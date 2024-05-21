import React from "react";
import MovieTerm from "../MovieTerm/movieTerm";
import {Link} from 'react-router-dom';
import ReactPaginate from 'react-paginate';


class Movies extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            page: 0,
            size: 5
        }
    }

    render() {
        const movies = this.getMoviesPage();
        return (
            <div className={"container mm-4 mt-5"}>
                <div className={"row"}>
                    <div className={"table-responsive"}>
                        <table className={"table table-striped"}>
                            <thead>
                            <tr>
                                <th scope={"col"}>Title</th>
                                <th scope={"col"}>Genre</th>
                                <th scope={"col"}>Year</th>
                                <th scope={"col"}>Average Ratings</th>

                            </tr>
                            </thead>
                            <tbody>
                            {movies}
                            </tbody>
                        </table>

                    </div>
                    <div className="col mb-3">
                        <div className="row">
                            <div className="col-sm-12 col-md-12">
                                <Link className={"btn btn-block btn-dark"} to={"/movies/create"}>Add new Movie</Link>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        );

    }
    handlePageClick = (data) => {
        let selected = data.selected;
        this.setState({
            page: selected
        })
    }

    getMoviesPage = () => {
        console.log("Test", this.props.movies);
        return this.props.movies?.map((term, index) => {
            return (
                <MovieTerm term={term} onDelete={this.props.onDelete} onEdit={this.props.onEdit} onReview={this.props.onReview}/>
            );
        })
    }
}

export default Movies;