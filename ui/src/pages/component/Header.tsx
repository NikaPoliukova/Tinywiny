import * as React from 'react';
import {useEffect, useState} from 'react';
import AppBar from '@mui/material/AppBar';
import Button from '@mui/material/Button';
import CssBaseline from '@mui/material/CssBaseline';
import Toolbar from '@mui/material/Toolbar';
import Typography from '@mui/material/Typography';
import Link from '@mui/material/Link';
import GlobalStyles from '@mui/material/GlobalStyles';
import {useParams} from "react-router-dom";
import BucketService from "../../services/BucketService";
import {Bucket} from "../../model/Bucket";

function PricingContent() {
    const {userId} = useParams();
    const [bucket, setBucket] = useState<Bucket>();
    useEffect(() => {
        BucketService.findBucketByUserId(Number(userId))
            .then(response => setBucket(response));
    }, []);
    return (
        <React.Fragment>
            <GlobalStyles styles={{ ul: { margin: 0, padding: 0, listStyle: 'none' } }} />
            <CssBaseline />
            <AppBar
                position="static"
                color="default"
                elevation={0}
                sx={{ borderBottom: (theme) => `1px solid ${theme.palette.divider}` }}
            >
                <Toolbar sx={{ flexWrap: 'wrap' }}>
                    <Typography variant="h6" color="inherit" noWrap sx={{ flexGrow: 1 }}>
                        Tiny-Winy
                    </Typography>
                    <nav>
                        <Link
                            variant="button"
                            color="text.primary"
                            href="/gallery"
                            sx={{ my: 1, mx: 1.5 }}
                        >
                            Gallery
                        </Link>
                        <Link
                            variant="button"
                            color="text.primary"
                            href="/products/toys"
                            sx={{ my: 1, mx: 1.5 }}
                        >
                            Products
                        </Link>
                        <Link
                            variant="button"
                            color="text.primary"
                            href="/bucket/${userId}"
                            sx={{ my: 1, mx: 1.5 }}
                        >
                            bucket
                        </Link>
                        <Link
                            variant="button"
                            color="text.primary"
                            href="/users/${userId}"
                            sx={{ my: 1, mx: 1.5 }}
                        >
                            My Page
                        </Link>
                        <Link
                            variant="button"
                            color="text.primary"
                            href="#"
                            sx={{ my: 1, mx: 1.5 }}
                        >
                           Contacts
                        </Link>
                    </nav>
                    <Button href="/login" variant="outlined" sx={{ my: 1, mx: 1.5 }}>
                        Login
                    </Button>
                </Toolbar>
            </AppBar>
        </React.Fragment>
    );
}

export default function Pricing() {
    return <PricingContent />;
}