import React from "react";
import {Link} from 'react-router-dom';


const movieTerm = (props) => {
    return (
        <tr>
            <td scope={"col"}>{props.term.title}</td>
            <td scope={"col"}>{props.term.genre}</td>
            <td scope={"col"}>{props.term.year}</td>
            <td scope={"col"}>{props.term.averageRating}</td>
            <td scope={"col"} className={"text-right"}>
                <Link className={"btn btn-warning ml-2"}
                      onClick={() => props.onReview(props.term.id)}
                      to={`/movies/${props.term.id}/reviews/create`}>
                    Add a review
                </Link>
            </td>

        </tr>

    )
}
export default movieTerm;