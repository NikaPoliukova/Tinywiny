import Header from 'pages/component/Header';
import React, {useEffect, useState} from 'react';
import {User} from "../../model/User";
import UserService from "../../services/UserService";
import {Button, Card, Table, TableCell, TableContainer, TableHead, TableRow} from "@mui/material";


const UserPage = () => {

    const [user, setUser] = useState<User>();
    useEffect(() => {
        UserService.getUser()
            .then(response => setUser(response));
    }, []);
    return (

        <div className="container mb-5 mt-5 row inner-wrap">
            <Header />
            <h3 className="mb-5" >User, {user?.userName}</h3>

            <div className="col-lg-6" >
                <div className="card"  >
                    <img src= "https://cdn-icons-png.flaticon.com/512/20/20079.png" />
                    <Button size="small">Update profile information</Button>
                </div>
            </div>
            <Card style={{width: 1000}}>
                <TableContainer>
                    <Table sx={{minWidth: 800}} aria-label="simple table">
                        <TableHead>
                            <TableRow>
                                <TableCell align="right">UserName</TableCell>
                                <TableCell align="right">{user?.userName}</TableCell>

                                <TableCell align="right">Password</TableCell>
                                <TableCell align="right"> {user?.password}</TableCell>

                                <TableCell align="right">Email</TableCell>
                                <TableCell align="right">{user?.email}</TableCell>

                                <TableCell align="right">PhoneNumber</TableCell>
                                <TableCell align="right">{user?.phoneNumber}</TableCell>

                                <Button
                                    type="submit"
                                    fullWidth
                                    variant="contained"
                                    sx={{mt: 1, mb: 1}}
                                    value={user?.userId}
                                >
                                    Orders
                                </Button>
                            </TableRow>
                        </TableHead>

                    </Table>
                </TableContainer>
            </Card>

        </div>


    );
}

export default UserPage;