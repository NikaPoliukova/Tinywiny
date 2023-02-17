import React, {useState} from 'react';
import Modal from "react-bootstrap/Modal";
import {Button, Form} from "react-bootstrap";
import Header from "../component/Header";
import {Box, Card, IconButton, Table, TableBody, TableCell, TableContainer, TableHead, TableRow} from "@mui/material";
import Typography from "@mui/material/Typography";
import Grid from "@mui/material/Grid";
import TextField from "@mui/material/TextField";
import {Footer} from "../component/Footer";
import {PhotoCamera} from "@mui/icons-material";


export default function CreateProduct () {
    const [product, setProduct] = useState()
    const [name, setName] = useState('')
    const [price, setPrice] = useState(0)
    const [count, setCount] = useState(0)
    return (
        <React.Fragment>
            <Header/>
            <h2> Create product</h2>
            <Card style={{width: 1000}}
                  sx={{
                      marginTop: 10,
                      display: 'flex',
                      flexDirection: 'column',
                      alignItems: 'center',

                  }}>

            </Card>
            <Typography variant="h6" gutterBottom align="center">
             Add details for product
            </Typography>

            <Box
                sx={{
                    marginTop: 10,
                    display: 'flex',
                    flexDirection: 'column',
                    alignItems: 'center',
                }}

            >  <Button variant="contained" >
                Upload photo
                <input hidden accept="image/*" multiple type="file" />
            </Button>
                <IconButton color="primary" aria-label="upload picture" component="label">
                    <input hidden accept="image/*" type="file" />
                    <PhotoCamera />
                </IconButton>

                <Grid sx={{minWidth: 800}} aria-label="simple table">

                    <Grid item xs={100}>
                        <TextField label="discription"/>

                    </Grid>

                    <Button color="secondary" variant="contained">Add product</Button>
                </Grid>
            </Box>
            <Footer/>
        </React.Fragment>
    );
}