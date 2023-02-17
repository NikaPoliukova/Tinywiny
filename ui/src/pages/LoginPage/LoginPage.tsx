import * as React from 'react';
import {
    Alert, AlertColor, AlertTitle,
    Avatar,
    Box,
    Button,
    Checkbox,
    Container,
    createTheme,
    CssBaseline,
    FormControlLabel,
    Grid,
    Link,
    TextField,
    ThemeProvider,
    Typography
} from "@mui/material";
import Header from "../component/Header";
import {Footer} from "../component/Footer";
import { redirect } from 'react-router-dom';
import AuthorizationService from "../../services/AuthorizationService";



const theme = createTheme();

export function SignIn() {
    const [login, setLogin] = React.useState("");
    const [password, setPassword] = React.useState("");

    const infoAlertType = "info" as AlertColor;
    const warningAlertType = "warning" as AlertColor;
    const successAlertType = "success" as AlertColor;

    const [alertType, setAlertType] = React.useState(infoAlertType);
    const [alertText, setAlertText] = React.useState('Input credentials');
    const [alertTitle, setAlertTitle] = React.useState('Info');

    const handleLoginChange = (event: { target: { value: React.SetStateAction<string>; }; }) => {
        setLogin(event.target.value);
    }

    const handlePasswordChange = (event: { target: { value: React.SetStateAction<string>; }; }) => {
        setPassword(event.target.value);
    }
    const handleSubmit = async (event: { preventDefault: () => void; }) => {
        event.preventDefault();
        console.log(login)

        const authorizationStatus = await AuthorizationService.login(login, password);

        handleAlert(authorizationStatus);
    };
    const handleAlert = (status :number) => {
        if (status == 200) {
            setAlertType(successAlertType);
            setAlertTitle('Success');
            setAlertText('Ok');
            redirect("http://localhost:3000/products/type/toys");
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
            <Header/>
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
                        Sign in
                    </Typography>
                    <Box component="form" onSubmit={handleSubmit} noValidate sx={{mt: 1}}>
                        <TextField
                            margin="normal"
                            required
                            fullWidth
                            id="login"
                            label="login"
                            name="login"
                            autoComplete="login"
                            autoFocus
                            value = {login}
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
                            value = {password}
                            onChange={handlePasswordChange}
                        />
                        <Alert severity={alertType}>
                            <AlertTitle>{alertTitle}</AlertTitle>
                            <strong>{alertText}</strong>
                        </Alert>
                        <FormControlLabel
                            control={<Checkbox value="remember" color="primary"/>}
                            label="Remember me"
                        />
                        <Button
                            type="submit"
                            fullWidth
                            variant="contained"
                            sx={{mt: 3, mb: 2}}

                        >
                            Sign In
                        </Button>
                        <Grid container>
                            <Grid item xs>
                                <Link href="/registration" variant="body2">
                                    Register
                                </Link>
                            </Grid>
                        </Grid>
                    </Box>
                </Box>
            </Container>
            <Footer/>
        </ThemeProvider>
    );
}
