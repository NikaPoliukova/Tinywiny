import React, {useEffect, useState} from 'react';
import {Button, Card, Table, TableBody, TableCell, TableContainer, TableHead, TableRow,} from "@mui/material";
import {Review} from "../../model/Review";
import ReviewService from "../../services/ReviewService";
import MyHeader from 'pages/component/MyHeader';
import {Footer} from "../component/Footer";


const ReviewsPage = () => {
    const [reviews, setReviews] = useState<Array<Review>>([]);


    useEffect(() => {
        ReviewService.getReviews()
            .then(response => setReviews(response));
    }, []);

    return (

        <div>
            <MyHeader />

                <h1>Reviews </h1>

            <Card style={{width: 1000}}>
                <TableContainer >
                    <Table sx={{minWidth: 800}} aria-label="simple table" >
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
            <Footer />
        </div>
    );
};

export default ReviewsPage;