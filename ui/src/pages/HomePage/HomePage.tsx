import * as React from 'react';
import Header from '../component/MyHeader';
import {Footer} from "../component/Footer";
import {Container} from "react-bootstrap";

export default function GalleryPage() {
    return (

        <React.Fragment>
            <Header />

            <Container >

                <p >
                   ABOUT TINY-WINY
                </p>
            </Container>
            <Footer />
        </React.Fragment>
    )

}


