import React from 'react';
import {Button} from "@mui/material";
import {Footer} from "../component/Footer";
import {Grid, Header, Icon} from "semantic-ui-react";
import MyHeader from 'pages/component/MyHeader';


export const AdminPage = () => {

    return (
        <div className="container mb-5 mt-5 row inner-wrap">
            <MyHeader/>
            <div>
                <Header as='h2' icon textAlign='center'>
                    <Icon name='user' circular size='big'/>
                    <Header.Content>Admin page</Header.Content>
                </Header>
            </div>
            <div>Добавить продукт</div>
            < Grid  container spacing={4}>
                <Button type="submit"
                        variant="contained"
                        sx={{mt: 3, mb: 2}}
                        href="/products">Add product </Button>
            </ Grid>
            <div>Просмотреть все заказы</div>
            < Grid  container spacing={4}>
                <Button type="submit"
                        variant="contained"
                        sx={{mt: 3, mb: 2}}
                        href="/orders"> Open list orders </Button>
            </ Grid>
            <div>Просмотреть все отзывы</div>
            < Grid  container spacing={4}>
                <Button type="submit"
                        variant="contained"
                        sx={{mt: 3, mb: 2}}
                        href="/admin/reviews"> Open list reviews </Button>
            </ Grid >
            <div>Просмотреть всех пользвоателей</div>
            < Grid  container spacing={4}>
                <Button type="submit"
                        variant="contained"
                        sx={{mt: 3, mb: 2}}
                        href="/users"> Open list users </Button>
            </ Grid>
            <div>Редактировать продукт</div>
            < Grid  container spacing={4}>
                <Button type="submit"
                        variant="contained"
                        sx={{mt: 3, mb: 2}}
                        href="/admin/products">Update products </Button>
            </ Grid>
            <Footer/>
        </div>
    );

}
