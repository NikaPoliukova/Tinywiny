import * as React from 'react';
import {
    Alert,
    AlertColor,
    AlertTitle,
    Avatar,
    Box,
    Button,
    Container,
    createTheme,
    CssBaseline,
    TextField,
    ThemeProvider,
    Typography
} from "@mui/material";
import AuthorizationService from "../../services/AuthorizationService";
import {redirect} from 'react-router-dom';
import {Footer} from "../component/Footer";
import MyHeader from 'pages/component/MyHeader';


const theme = createTheme();

export function SignIn() {
    const [userName, setUserName] = React.useState("");
    const [password, setPassword] = React.useState("");

    const infoAlertType = "info" as AlertColor;
    const warningAlertType = "warning" as AlertColor;
    const successAlertType = "success" as AlertColor;

    const [alertType, setAlertType] = React.useState(infoAlertType);
    const [alertText, setAlertText] = React.useState('Input credentials');
    const [alertTitle, setAlertTitle] = React.useState('Info');

    const handleLoginChange = (event: { target: { value: React.SetStateAction<string>; }; }) => {
        setUserName(event.target.value);
    }

    const handlePasswordChange = (event: { target: { value: React.SetStateAction<string>; }; }) => {
        setPassword(event.target.value);
    }
    const handleSubmit = async (event: { preventDefault: () => void; }) => {
        event.preventDefault();

        const authorizationStatus = await AuthorizationService.login(userName, password);

        handleAlert(authorizationStatus);
    };
    const handleAlert = (status :number) => {
        if (status == 200) {
            setAlertType(successAlertType);
            setAlertTitle('Success');
            setAlertText('Ok');
            redirect("http://localhost:3000/users/userId");
            return;
        } else {
            setAlertType(warningAlertType);
            setAlertTitle('Warning');
            setAlertText('Credentials are not correct');
            return;
        }
    };

    return (

        <ThemeProvider theme={theme}>
            <MyHeader />
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
                        Log in
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
                        <Button
                            type="submit"
                            fullWidth
                            variant="contained"
                            sx={{mt: 3, mb: 2}}
                            href="/users/"
                        >
                            Sign In
                        </Button>
                        <Button
                            type="submit"
                            fullWidth
                            variant="contained"
                            sx={{mt: 3, mb: 2}}
                            href="/registration"
                        >
                            Registry
                        </Button>
                        <Alert severity={alertType}>
                            <AlertTitle>{alertTitle}</AlertTitle>
                            <strong>{alertText}</strong>
                        </Alert>
                    </Box>
                </Box>
            </Container>
            <Footer/>
        </ThemeProvider>
    );
}

