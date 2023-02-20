import React, {useEffect, useState} from 'react';
import {User} from "../../model/User";
import UserService from "../../services/UserService";
import {Button, Card, Table, TableBody, TableCell, TableContainer, TableHead, TableRow} from "@mui/material";
import {Link, useParams} from "react-router-dom";
import {Footer} from "../component/Footer";
import {Header, Icon} from "semantic-ui-react";
import MyHeader from 'pages/component/MyHeader';


export const UserPage = () => {
    const Profile = () => {
        const {userId} = useParams();
        const [user, setUser] = useState<User>();
        useEffect(() => {
            UserService.getUser(Number(userId))
                .then(response => setUser(response));
        }, []);
        return (
            <div className="container mb-5 mt-5 row inner-wrap">
                <MyHeader/>
                <div>
                    <Header as='h2' icon textAlign='center'>
                        <Icon name='user' circular size='big'/>
                        <Header.Content>User, {user?.userName}</Header.Content>
                    </Header>

                </div>

                <div className="col-lg-6">
                    <div className="card">
                        <Button size="small">Update profile information</Button>
                    </div>
                </div>
                <Card style={{width: 1000}}>
                    <TableContainer>
                        <Table sx={{minWidth: 800}} aria-label="simple table">
                            <TableHead>
                                <TableRow>
                                    <TableCell align="left">UserName</TableCell>
                                    <TableCell align="left">Password</TableCell>
                                    <TableCell align="left">Email</TableCell>
                                    <TableCell align="left">PhoneNumber</TableCell>
                                </TableRow>
                            </TableHead>
                            <TableBody>
                                <TableRow
                                    key={user?.userId}
                                    sx={{'&:last-child td, &:last-child th': {border: 0}}}>
                                    <TableCell component="th" scope="row">
                                        {user?.userName}
                                    </TableCell>
                                    <TableCell component="th" scope="row">
                                        {user?.password}
                                    </TableCell>
                                    <TableCell align="right">
                                        {user?.email}
                                    </TableCell>
                                    <TableCell align="right">
                                        {user?.phoneNumber}
                                    </TableCell>
                                </TableRow>
                            </TableBody>
                        </Table>
                    </TableContainer>
                </Card>
                <Button
                    component={Link}
                    type="submit"
                    size="small"
                    sx={{mt: 1, mb: 1}}
                    to={'/orders/' + userId}
                >
                    Orders
                </Button>
                <Footer/>
            </div>
        );
    }
    return (
        <>
            <Profile/>
        </>
    )
}
