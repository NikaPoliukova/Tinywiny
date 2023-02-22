import React, {useEffect, useState} from 'react'
import {Button, Comment, Form} from 'semantic-ui-react'
import {Review} from "../../model/Review";
import ReviewService from "../../services/ReviewService";
import {useNavigate} from "react-router-dom";
import MyHeader from 'pages/component/MyHeader';


function ReviewForUsers ()  {
    const [reviews, setReviews] = useState<Array<Review>>([]);
    const [textReview, setText] = useState('');
    const [userId, setUserId] = useState(Number); //ДОСТАТЬ ИЗ СЕССИИ
    const navigate = useNavigate();
    const addReview = () => {
        const newReview: Review = {
            textReview,
            userId
        };
        ReviewService.saveReview(newReview).then(response => navigate("/review"));
    }
    useEffect(() => {
        ReviewService.getReviews()
            .then(response => setReviews(response));
    }, []);
    return (


        <Comment.Group>
            {reviews.map((review) => (
                <Comment >
                    <Comment.Content>
                        <Comment.Author> user: {review.userId}</Comment.Author>
                        <Comment.Metadata>
                            <div> date: {review.createdDate}</div>
                        </Comment.Metadata>
                        <Comment.Text>
                            <p>
                                {review.textReview}
                            </p>
                        </Comment.Text>
                    </Comment.Content>
                </Comment>
            ))}
            <Form>
                <Form.TextArea label='Add review' placeholder='Write review...'
                               value={textReview}
                               onChange={e => setText(e.target.value)}/>
                <Button
                    type="submit"
                    variant="contained"
                    sx={{mt: 3, mb: 2}}
                    onClick={addReview}
                >
                    Add
                </Button>
            </Form>
        </Comment.Group>
    )
};

export default ReviewForUsers