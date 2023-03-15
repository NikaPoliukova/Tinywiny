import React, {useState} from 'react';
import {User} from "../../model/User";
import UserService from "../../services/UserService";
import {Card, TableContainer} from "@mui/material";
import {useNavigate} from "react-router-dom";
import {Footer} from "../component/Footer";
import {Form, Header, Icon, Table} from "semantic-ui-react";
import MyHeader from 'pages/component/MyHeader';
import {useSessionStore} from "../../store";


export const UpdateUserPage = () => {
    const Profile = () => {
        const me = useSessionStore(state => state.user);
        const [userName, setUserName] = useState("");
        const [password, setPassword] = useState("");
        const [email, setEmail] = useState("");
        const [phoneNumber, setPhoneNumber] = useState("");
        const navigate = useNavigate();
        const userId = me?.userId;

        const updateUser = () => {
            const user: User = {
                userId,
                userName,
                password,
                email,
                phoneNumber
            };
            UserService.updateUser(user).then(() => navigate(`/gallery`));

        }
        return (
            <div className="card">
                <MyHeader/>
                <div>
                    <Header as='h2' icon textAlign='center'>
                        <Icon name='user' circular size='big'/>
                        <Header.Content>User, {me?.userName}</Header.Content>
                    </Header>
                </div>
                <Card style={{width: 1500}}>
                    <TableContainer>
                        <Table>
                            <Table.Body>
                                <Table.Row>
                                    <Table.Cell width={1}>User Name</Table.Cell>

                                    <Form.Input fluid placeholder='Write new name'
                                                value={userName}
                                                onChange={e => setUserName(e.target.value)}/>
                                </Table.Row>
                                <Table.Row>
                                    <Table.Cell width={1}>Password</Table.Cell>

                                    <Form.Input fluid placeholder='Write new password'
                                                value={password}
                                                onChange={e => setPassword(e.target.value)}/>
                                </Table.Row>
                                <Table.Row>
                                    <Table.Cell width={1}>Email</Table.Cell>

                                    <Form.Input fluid placeholder='Write new email'
                                                value={email}
                                                onChange={e => setEmail(e.target.value)}/>
                                </Table.Row>
                                <Table.Row>
                                    <Table.Cell width={1}>PhoneNumber</Table.Cell>
                                    <Form.Input fluid placeholder='Write new phoneNumber'
                                                value={phoneNumber}
                                                onChange={e => setPhoneNumber(e.target.value)}/>
                                </Table.Row>
                            </Table.Body>
                        </Table>
                        <Form.Button
                            onClick={updateUser}
                        >Update user</Form.Button>
                    </TableContainer>
                </Card>
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
