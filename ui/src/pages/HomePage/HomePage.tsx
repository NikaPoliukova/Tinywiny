import * as React from 'react';

import ImageList from '@mui/material/ImageList';
import ImageListItem from '@mui/material/ImageListItem';
import ImageListItemBar from '@mui/material/ImageListItemBar';
import Header from '../component/Header';


export default function GalleryPage() {
    return (

        <React.Fragment>
            <Header/>
            <ImageList variant="masonry" cols={3} gap={6}
                       sx={{
                           marginTop: 1,
                           flexDirection: 'column',
                           alignItems: 'center',

                       }}>
                {itemData.map((item) => (
                    <ImageListItem key={item.img}>
                        <img
                            src={`${item.img}?w=248&fit=crop&auto=format`}
                            srcSet={`${item.img}?w=248&fit=crop&auto=format&dpr=2 2x`}

                            loading="lazy"
                        />
                        <ImageListItemBar position="below"/>
                    </ImageListItem>
                ))}
            </ImageList>
        </React.Fragment>
    )

}

const itemData = [
    {
        img: 'https://images.unsplash.com/photo-1549388604-817d15aa0110',
    },
    {
        img: 'https://images.unsplash.com/photo-1525097487452-6278ff080c31',
    },
    {
        img: 'https://images.unsplash.com/photo-1523413651479-597eb2da0ad6',
    },
    {
        img: 'https://images.unsplash.com/photo-1563298723-dcfebaa392e3',
    },
    {
        img: 'https://images.unsplash.com/photo-1588436706487-9d55d73a39e3',
    },
    {
        img: 'https://images.unsplash.com/photo-1574180045827-681f8a1a9622',
    },
    {
        img: 'https://images.unsplash.com/photo-1530731141654-5993c3016c77',
    },
    {
        img: 'https://images.unsplash.com/photo-1481277542470-605612bd2d61',
    },
    {
        img: 'https://images.unsplash.com/photo-1517487881594-2787fef5ebf7',
    },
    {
        img: 'https://images.unsplash.com/photo-1516455207990-7a41ce80f7ee',
    },
    {
        img: 'https://images.unsplash.com/photo-1597262975002-c5c3b14bbd62',
    },
    {
        img: 'https://images.unsplash.com/photo-1519710164239-da123dc03ef4',

    },
];
