import React from 'react';
import {useHistory} from 'react-router-dom';

const ReviewAdd = (props) => {

    const history = useHistory();
    const [formData, updateFormData] = React.useState({
        review: ""
    })

    const handleChange = (e) => {
        updateFormData({
            ...formData,
            [e.target.name]: e.target.value.trim()
        })
    }

    const onFormSubmit = (e) => {
        e.preventDefault();
        const review = formData.review;


        props.onAddReview(props.movie.id, review);
        history.push("/movies");
    }

    return(
        <div className="row mt-5">
            <div className="col-md-5">
                <form onSubmit={onFormSubmit}>
                    <div className="form-group">
                        <label htmlFor="Review">Review</label>
                        <input type="text"
                               className="form-control"
                               id="review"
                               name="review"
                               required
                               placeholder="Write a review"
                               onChange={handleChange}
                        />
                    </div>


                    <button id="submit" type="submit" className="btn btn-primary">Create</button>
                </form>
            </div>
        </div>
    )
}

export default ReviewAdd;
