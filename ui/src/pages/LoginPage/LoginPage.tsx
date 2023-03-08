import {
    Alert,
    AlertColor,
    AlertTitle,
    Avatar,
    Box,
    Container,
    createTheme,
    CssBaseline,
    TextField,
    ThemeProvider,
    Typography
} from "@mui/material";
import {Button} from 'semantic-ui-react'
import PersonIcon from '@mui/icons-material/Person';
import React from "react";
import AuthorizationService from "../../services/AuthorizationService";
import { useNavigate } from "react-router-dom";


const theme = createTheme();

const SignIn = () => {
    const infoAlertType = "info" as AlertColor;
    const warningAlertType = "warning" as AlertColor;
    const successAlertType = "success" as AlertColor;

    const [alertType, setAlertType] = React.useState(infoAlertType);
    const [alertText, setAlertText] = React.useState('Input credentials');
    const [alertTitle, setAlertTitle] = React.useState('Info');

    const [userName, setUserName] = React.useState("");
    const [password, setPassword] = React.useState("");

    const navigate = useNavigate();
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

    const handleAlert = (status: number) => {
        if (status == 200) {
            setAlertType(successAlertType);
            setAlertTitle('Success');
            setAlertText('Ok');
            navigate("/products/type/toys");

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
            <Box sx={{
                display: 'flex',
                flexDirection: 'column',
                alignItems: 'end'
            }}
            >
            </Box>
            <Container component="main" maxWidth="xs">
                <CssBaseline/>
                <Box
                    sx={{
                        marginTop: 15,
                        display: 'flex',
                        flexDirection: 'column',
                        alignItems: 'center',
                    }}
                >
                    <Avatar sx={{m: 1, bgcolor: 'secondary.main'}}>
                        <PersonIcon/>
                    </Avatar>
                    <Typography component="h1" variant="h5">
                        Sign in
                    </Typography>
                    <Box component="form" onSubmit={handleSubmit} noValidate sx={{mt: 1}}>
                        <TextField
                            margin="normal"
                            required
                            fullWidth
                            id="userName"
                            label="userName"
                            name="userName"
                            autoComplete="current-login"
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
                        <Alert severity={alertType}>
                            <AlertTitle>{alertTitle}</AlertTitle>
                            <strong>{alertText}</strong>
                        </Alert>
                        <Button
                            type="submit"
                            fullWidth
                            variant="contained"
                            sx={{mt: 3, mb: 2}}

                        >
                            Sign In
                        </Button>
                    </Box>
                </Box>
            </Container>
        </ThemeProvider>
    );
};

export default SignIn;

