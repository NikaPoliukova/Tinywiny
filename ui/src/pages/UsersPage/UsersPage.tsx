import React, {useEffect, useState} from 'react';
import UserService from "../../services/UserService";
import {User} from '../../model/User';
import {
    Card,
    Table,
    TableBody,
    TableCell,
    TableContainer,
    TableHead,
    TableRow,
    Typography
} from "@mui/material";
import Header from "../component/Header";


const UsersPage = () => {
    const [users, setUsers] = useState<Array<User>>([]);
    const [error, setError] = useState<string>('');

    useEffect(() => {
        UserService.getUsers()
            .then(response => setUsers(response))
            .catch(error => setError(error.message));
    }, []);


    return (
        <div>
            <Header/>
            {error}
            <Typography component="h1" variant="h5">
                <h1>Users</h1>
            </Typography>
            <Card style={{width: 1000}}>
                <TableContainer>
                    <Table sx={{minWidth: 800}} aria-label="simple table">
                        <TableHead>
                            <TableRow>
                                <TableCell align="right">UserName</TableCell>
                                <TableCell align="right">Password</TableCell>
                                <TableCell align="right">Email</TableCell>
                                <TableCell align="right">PhoneNumber</TableCell>
                            </TableRow>
                        </TableHead>
                        <TableBody>
                            {users.map((user) => (
                                <TableRow
                                    key={user.userName}
                                    sx={{'&:last-child td, &:last-child th': {border: 0}}}
                                >
                                    <TableCell component="th" scope="row">
                                        {user.userName}
                                    </TableCell>
                                    <TableCell component="th" scope="row">
                                        {user.password}
                                    </TableCell>
                                    <TableCell align="right">
                                        {user.email}
                                    </TableCell>
                                    <TableCell align="right">
                                        {user.phoneNumber}
                                    </TableCell>

                                </TableRow>
                            ))}
                        </TableBody>
                    </Table>
                </TableContainer>
            </Card>
        </div>
    );
};

export default UsersPage;