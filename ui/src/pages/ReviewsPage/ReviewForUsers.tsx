import React, {useEffect, useState} from 'react'
import {Button, Comment, Container, Form, Icon} from 'semantic-ui-react'
import {Review} from "../../model/Review";
import ReviewService from "../../services/ReviewService";
import {useNavigate} from "react-router-dom";
import {Col, Row} from "react-bootstrap";
import Card from "@mui/material/Card";
import MyHeader from 'pages/component/MyHeader';


function ReviewForUsers() {
    const [reviews, setReviews] = useState<Array<Review>>([]);
    const [textReview, setText] = useState<string>('');

    const navigate = useNavigate();
    const addReview = () => {
        ReviewService.saveReview(textReview).then(() => navigate("/products/type/toys"));
    }
    useEffect(() => {
        ReviewService.getReviews()
            .then(response => setReviews(response));
    }, []);
    return (
        <Container>
            <MyHeader/>
            <Row>
                <Col md={10}>
                    <Card sx={{height: '100%', display: 'flex', flexDirection: 'column'}}>
                        <Comment.Group>
                            {reviews.map((review) => (
                                <Comment>
                                    <Icon name='user' color='brown'/>
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
                    </Card>
                </Col>
            </Row>
        </Container>
    )
};

export default ReviewForUsers