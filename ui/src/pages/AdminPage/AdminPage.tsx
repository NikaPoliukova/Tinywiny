import React from 'react';
import {Button, TableCell} from "@mui/material";
import {Footer} from "../component/Footer";
import {Header, Icon} from "semantic-ui-react";
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

            <TableCell>
                <div>Добавить продукт</div>
                <Button type="submit"
                        variant="contained"
                        sx={{mt: 3, mb: 2}}
                        href="/products/create">Add product </Button>
            </TableCell>
            <TableCell>
                <div>Просмотреть все заказы</div>
                <Button type="submit"
                        variant="contained"
                        sx={{mt: 3, mb: 2}}
                        href="/orders"> Open list orders </Button>
            </TableCell>
            <TableCell>
                <div>Просмотреть все отзывы</div>
                <Button type="submit"
                        variant="contained"
                        sx={{mt: 3, mb: 2}}
                        href="/reviews"> Open list reviews </Button>
                </TableCell>
                <TableCell>
                    <div>Просмотреть всех пользвоателей</div>
                    <Button type="submit"
                            variant="contained"
                            sx={{mt: 3, mb: 2}}
                            href="/users"> Open list users </Button>
                </TableCell>
            <TableCell>
                <div>Редактировать продукт</div>
                <Button type="submit"
                        variant="contained"
                        sx={{mt: 3, mb: 2}}
                        href="/products">Update product </Button>
            </TableCell>
                <Footer/>
        </div>
);

}
