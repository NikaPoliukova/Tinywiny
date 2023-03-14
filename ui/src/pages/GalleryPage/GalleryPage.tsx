import * as React from 'react';

import ImageList from '@mui/material/ImageList';
import ImageListItem from '@mui/material/ImageListItem';
import ImageListItemBar from '@mui/material/ImageListItemBar';
import Header from '../component/MyHeader';
import {Footer} from "../component/Footer";
import {Sidebar} from "../component/SideBar";

export default function GalleryPage() {
    return (

        <React.Fragment>
            <Header/>
            <Sidebar/>
            <ImageList variant="masonry" cols={3} gap={6}
                       sx={{
                           marginTop: 3,
                           flexDirection: 'column',
                           alignItems: 'center',
                           fontSize: 100,


                       }}>
                {itemData.map((item) => (
                    <ImageListItem key={item.img}>
                        <img src={`${item.img}?w=248&fit=crop&auto=format`}
                            srcSet={`${item.img}?w=248&fit=crop&auto=format&dpr=2 2x`}
                            loading="lazy"

                        />
                        <ImageListItemBar position="below"/>
                    </ImageListItem>
                ))}
            </ImageList>
            <Footer/>
        </React.Fragment>
    )

}

const itemData = [

    {
        img: "./image/изображение_viber_2023-01-15_00-35-33-319.jpg",

    },
    {
        img: './image/изображение_viber_2023-01-15_00-34-24-976.jpg',

    },
    {
        img: './image/изображение_viber_2023-01-15_00-34-25-567.jpg',

    },

];
