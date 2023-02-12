import * as React from 'react';
import Button from '@mui/material/Button';
import Card from '@mui/material/Card';
import CardActions from '@mui/material/CardActions';
import CardContent from '@mui/material/CardContent';
import CardMedia from '@mui/material/CardMedia';
import CssBaseline from '@mui/material/CssBaseline';
import Grid from '@mui/material/Grid';
import Stack from '@mui/material/Stack';
import Box from '@mui/material/Box';
import Typography from '@mui/material/Typography';
import Container from '@mui/material/Container';
import {createTheme, ThemeProvider} from '@mui/material/styles';
import Header from "../component/Header";


const theme = createTheme();

function ProductPage() {
    return (
        <ThemeProvider theme={theme}>
            <CssBaseline/>
            <Header/>

            <main>
                <Box component='nav' max-height='200vh'
                     sx={{
                         bgcolor: 'background.paper',
                         pt: 2,
                         pb: 2,
                     }}
                >
                    <Container maxWidth="sm">
                        <Typography
                            component="h2"
                            variant="h3"
                            align="right"
                            color="text.primary"
                            gutterBottom
                        >
                            Product
                        </Typography>
                        <Typography
                            component="h5"
                            variant="h5"
                            align="right"
                            color="text.primary"
                            gutterBottom >
                            ОПИСАНИЕ ТОВАРА

                        </Typography>
                        <Stack
                            sx={{pt: 4}}
                            direction="row"
                            spacing={2}
                            justifyContent="center"
                        >
                        </Stack>
                        <CardActions>
                            <Button size="small">Add in bucket</Button>
                        </CardActions>
                    </Container>
                </Box>

                <Container sx={{py: 8}} maxWidth="md">

                    <Grid container spacing={4}>

                        <Grid item  xs={10} sm={6} md={4}>
                            <Card
                                sx={{height: '100%', display: 'flex', flexDirection: 'column'}}
                            >
                                <CardMedia
                                    component="img"
                                    sx={{
                                        pt: '1%',
                                    }}
                                    image="https://source.unsplash.com/random"
                                    alt="random"
                                />
                                <CardContent sx={{flexGrow: 1}}>
                                    <Typography gutterBottom variant="h5" component="h2">
                                        Product
                                    </Typography>
                                </CardContent>
                            </Card>
                        </Grid>

                    </Grid>
                </Container>
            </main>
        </ThemeProvider>
    );
}
export default ProductPage;