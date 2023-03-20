import * as React from 'react';

import ImageList from '@mui/material/ImageList';
import ImageListItem from '@mui/material/ImageListItem';
import PhotoAlbum from "react-photo-album";
import ImageListItemBar from '@mui/material/ImageListItemBar';
import Header from '../component/MyHeader';
import {Footer} from "../component/Footer";
import {Sidebar} from "../component/SideBar";
import {Container} from "@mui/material";

export default function GalleryPage() {
    return (

        <React.Fragment>
            <Header/>
            <Sidebar/>
            <Container >
            <ImageList variant="woven" cols={3} gap={3}
                       sx={{
                           marginTop: 3,
                           flexBasis: 'auto',
                           alignItems: 'center',
                           fontSize: 100,
                       }}
            >
                {itemData.map((item) => (
                    <ImageListItem key={item.img}>
                        <img src={`${item.img}`}
                             width={100}
                             height={100}
                             srcSet={`${item.img}?w=248&fit=crop&auto=format&dpr=2 2x`}
                             loading="lazy"

                        />
                        <ImageListItemBar position="below"/>
                    </ImageListItem>
                ))}
            </ImageList>
            </Container>
            <Footer/>
        </React.Fragment>
    )

}

const itemData = [

    {
        img: "./image/four.png",
        width: 50,
        height: 50,
    },
    {
        img: './image/one.png',
        width: 50,
        height: 50,
    },
    {
        img: './image/three.png',
        width: 50,
        height: 50,
    },
    {
        img: './image/two.png',
        width: 50,
        height: 50,
    },
    {
        img: './image/five.png',
        width: 50,
        height: 50,
    },
    {
        img: './image/six.png',
        width: 50,
        height: 50,
    },

];
