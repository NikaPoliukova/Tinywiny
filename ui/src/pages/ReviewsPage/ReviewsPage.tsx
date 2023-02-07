import React, {useEffect, useState} from 'react';
import {
    Button,
    Card,
    Table,
    TableBody,
    TableCell,
    TableContainer,
    TableHead,
    TableRow,
    Typography
} from "@mui/material";
import {Review} from "../../model/Review";
import ReviewService from "../../services/ReviewService";
import Grid from "@mui/material/Grid";


const ReviewsPage = () => {
    const [reviews, setReviews] = useState<Array<Review>>([]);
    const [error, setError] = useState<string>('');

    useEffect(() => {
        ReviewService.getReviews()
            .then(response => setReviews(response))
            .catch(error => setError(error.message));
    }, []);


    return (
        <div>
            {error}
            <Typography component="h1" variant="h5">
                <h1>Reviews</h1>
            </Typography>
            <Card style={{width: 1000}}>
                <TableContainer>
                    <Table sx={{minWidth: 800}} aria-label="simple table">
                        <TableHead>
                            <TableRow>
                                <TableCell align="left">Id review</TableCell>
                                <TableCell align="left">Text</TableCell>
                                <TableCell align="left">Data created</TableCell>
                                <TableCell align="left">User Id</TableCell>
                            </TableRow>
                        </TableHead>
                        <TableBody>
                            {reviews.map((review) => (
                                <TableRow
                                    key={review.id}
                                    sx={{'&:last-child td, &:last-child th': {border: 0}}}
                                >
                                    <TableCell component="th" scope="row">
                                        {review.id}
                                    </TableCell>
                                    <TableCell component="th" scope="row" align="left">
                                        {review.textReview}
                                    </TableCell>
                                    <TableCell align="left">
                                        {review.createdDate}
                                    </TableCell>
                                    <TableCell align="left">
                                        {review.userId}
                                    </TableCell>
                                    <Button
                                        type="submit"
                                        fullWidth
                                        variant="contained"
                                        sx={{mt: 1, mb: 1}}
                                    >
                                       Delete
                                    </Button>
                                </TableRow>
                            ))}
                        </TableBody>
                    </Table>
                </TableContainer>
            </Card>
        </div>
    );
};

export default ReviewsPage;