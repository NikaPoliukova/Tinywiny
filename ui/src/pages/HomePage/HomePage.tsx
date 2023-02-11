import React, {useEffect, useState} from 'react';
import {Card, Table, TableBody, TableCell, TableContainer, TableHead, TableRow, Typography} from "@mui/material";
import BucketService from "../../services/BucketService";
import {Bucket} from "../../model/Bucket";


const BucketPage = () => {
    const [bucket, setBucket] = useState<Bucket>();
    useEffect(() => {
        BucketService.getBucket()
            .then(response => setBucket(response));
    }, []);

    return (
        <div>

            <Typography component="h1" variant="h5">
                <h1>Bucket {bucket?.bucketId}</h1>
            </Typography>
            <Card style={{width: 1000}}>
                <TableContainer>
                    <Table sx={{minWidth: 800}} aria-label="simple table">
                        <TableHead>
                            <TableRow>
                                <TableCell align="right">Product in bucket</TableCell>
                            </TableRow>
                        </TableHead>
                        <TableBody>
                            <TableRow>
                                <TableCell component="th" scope="row">

                                    <div>

                                        list products in bucket

                                    </div>
                                </TableCell>
                            </TableRow>
                        </TableBody>
                    </Table>
                </TableContainer>
            </Card>
        </div>
);
};

export default BucketPage;