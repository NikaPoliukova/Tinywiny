import * as React from 'react';
import {Container} from "@mui/material";
import {Footer} from "./Footer";
import HeaderForNoAuthorized from "./HeaderForNoAuthorized";

export const Authentication = ({children}: any) => {
    return (
        <>
            <Container
                disableGutters
                maxWidth="xl"
                sx={{minHeight: '100vh'}}>
                <HeaderForNoAuthorized/>
                {children}
                <Footer/>
            </Container>
        </>
    )
}