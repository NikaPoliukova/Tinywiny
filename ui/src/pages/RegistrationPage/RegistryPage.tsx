import * as React from 'react';
import {useState} from 'react';
import {Avatar, Box, Container, createTheme, CssBaseline, TextField, ThemeProvider, Typography} from "@mui/material";
import {Footer} from "../component/Footer";
import {User} from "../../model/User";
import UserService from "../../services/UserService";
import {useNavigate} from "react-router-dom";
import HeaderForNoAuthorized from "../component/HeaderForNoAuthorized";
import {Button} from 'semantic-ui-react'


const theme = createTheme();

export function Registry() {
    const [userName, setUserName] = useState("");
    const [password, setPassword] = useState("");
    const [email, setEmail] = useState("");
    const [phoneNumber, setPhoneNumber] = useState("");

    const navigate = useNavigate();
    const addUser = () => {
        const user: User = {
            userName,
            password,
            email,
            phoneNumber
        };
        UserService.saveUser(user).then(() => navigate("/login"));
    }

    const handleSubmit = (event: React.FormEvent<HTMLFormElement>) => {
        event.preventDefault();
    };
    const handleLoginChange = (event: { target: { value: React.SetStateAction<string>; }; }) => {
        setUserName(event.target.value);
    }

    const handlePasswordChange = (event: { target: { value: React.SetStateAction<string>; }; }) => {
        setPassword(event.target.value);
    }
    const handleEmailChange = (event: { target: { value: React.SetStateAction<string>; }; }) => {
        setEmail(event.target.value);
    }

    const handlePhoneNumberChange = (event: { target: { value: React.SetStateAction<string>; }; }) => {
        setPhoneNumber(event.target.value);
    }

    return (
        <ThemeProvider theme={theme}>
            <HeaderForNoAuthorized />
            <Container component="main" maxWidth="xs">
                <CssBaseline/>
                <Box
                    sx={{
                        marginTop: 8,
                        display: 'flex',
                        flexDirection: 'column',
                        alignItems: 'center',
                    }}
                >
                    <Avatar sx={{m: 1, bgcolor: 'secondary.main'}}>
                    </Avatar>
                    <Typography component="h1" variant="h5">
                        Registration
                    </Typography>
                    <Box component="form" onSubmit={handleSubmit} noValidate sx={{mt: 1}}>
                        <TextField
                            margin="normal"
                            required
                            fullWidth
                            id="userName"
                            label="UserName"
                            name="userName"
                            autoComplete="userName"
                            autoFocus
                            value={userName}
                            onChange={handleLoginChange}
                        />
                        <TextField
                            margin="normal"
                            required
                            fullWidth
                            name="password"
                            label="Password"
                            type="password"
                            id="password"
                            autoComplete="current-password"
                            value={password}
                            onChange={handlePasswordChange}
                        />
                        <TextField
                            margin="normal"
                            required
                            fullWidth
                            id="email"
                            label="Email"
                            name="email"
                            autoComplete="email"
                            autoFocus
                            value={email}
                            onChange={handleEmailChange}
                        />
                        <TextField
                            margin="normal"
                            required
                            fullWidth
                            id="phoneNumber"
                            label="PhoneNumber"
                            name="phoneNumber"
                            autoComplete="phoneNumber"
                            autoFocus
                            value={phoneNumber}
                            onChange={handlePhoneNumberChange}
                        />
                        <Button
                            basic color='brown'
                            type="submit"
                            variant="outlined"
                            sx={{mt: 3, mb: 2}}
                            onClick={addUser}
                        >
                            Registry
                        </Button>
                        <Button
                            basic color='brown'
                            content='Login'
                            href="/login"
                            variant="outlined"
                            sx={{my: 1, mx: 1.5}}>
                        </Button>

                    </Box>
                </Box>
            </Container>
            <Footer/>
        </ThemeProvider>
    );

}


