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
import Sidebar from "../component/SideBar";

const cards = [1, 2, 3, 4, 5, 6, 7, 8, 9];

const theme = createTheme();

export function Products() {
    return (
        <ThemeProvider theme={theme}>
            <CssBaseline/>
            <Header/>
            <Sidebar/>
            <main>
                <Box component='nav' max-height='200vh'
                     sx={{
                         bgcolor: 'background.paper',
                         pt: 8,
                         pb: 6,
                     }}
                >
                    <Container maxWidth="sm">
                        <Typography
                            component="h1"
                            variant="h2"
                            align="center"
                            color="text.primary"
                            gutterBottom
                        >
                            Products
                        </Typography>
                        <Stack
                            sx={{pt: 4}}
                            direction="row"
                            spacing={2}
                            justifyContent="center"
                        >
                        </Stack>
                    </Container>
                </Box>

                <Container sx={{py: 8}} maxWidth="md">

                    <Grid container spacing={4}>
                        {cards.map((card) => (
                            <Grid item key={card} xs={10} sm={6} md={4}>
                                <Card
                                    sx={{height: '100%', display: 'flex', flexDirection: 'column'}}
                                >
                                    <CardMedia
                                        component="img"
                                        sx={{
                                            pt: '2%',
                                        }}
                                        image="https://source.unsplash.com/random"
                                        alt="random"
                                    />
                                    <CardContent sx={{flexGrow: 1}}>
                                        <Typography gutterBottom variant="h5" component="h2">
                                            Product
                                        </Typography>
                                        <Typography>
                                            ОПИСАНИЕ ТОВАРА
                                        </Typography>
                                    </CardContent>
                                    <CardActions>
                                        <Button size="small">Add in bucket</Button>

                                    </CardActions>
                                </Card>
                            </Grid>
                        ))}
                    </Grid>
                </Container>
            </main>
        </ThemeProvider>
    );
}
