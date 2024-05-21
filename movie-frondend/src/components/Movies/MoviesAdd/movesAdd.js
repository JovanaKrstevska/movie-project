import React from 'react';
import {useHistory} from 'react-router-dom';

const MovieAdd = (props) => {

    const history = useHistory();
    const [formData, updateFormData] = React.useState({
        title: "",
        description: "",
        genre: "",
        year: 0,
    })

    const handleChange = (e) => {
        updateFormData({
            ...formData,
            [e.target.name]: e.target.value.trim()
        })
    }

    const onFormSubmit = (e) => {
        e.preventDefault();
        const title = formData.title;
        const description = formData.description;
        const genre = formData.genre;
        const year = formData.year;


        props.onAddMovie(title, description, genre, year);
        history.push("/movies");
    }

    return(
        <div className="row mt-5">
            <div className="col-md-5">
                <form onSubmit={onFormSubmit}>
                    <div className="form-group">
                        <label htmlFor="title">Movie Title</label>
                        <input type="text"
                               className="form-control"
                               id="title"
                               name="title"
                               required
                               placeholder="Enter movie title"
                               onChange={handleChange}
                        />
                    </div>
                    <div className="form-group">
                        <label>Description</label>
                        <input type="text"
                               className="form-control"
                               id="description"
                               name="description"
                               required
                               placeholder="Write description"
                               onChange={handleChange}
                        />
                    </div>
                    <div className="form-group">
                        <label>Genre</label>
                        <input type="text"
                               className="form-control"
                               id="genre"
                               name="genre"
                               required
                               placeholder="Type of genre"
                               onChange={handleChange}
                        />
                    </div>
                    <div className="form-group">
                        <label htmlFor="year">Year</label>
                        <input type="text"
                               className="form-control"
                               id="year"
                               name="year"
                               required
                               placeholder="Release year"
                               onChange={handleChange}
                        />
                    </div>


                    <button id="submit" type="submit" className="btn btn-primary">Submit</button>
                </form>
            </div>
        </div>
    )
}

export default MovieAdd;
