import * as React from 'react';
import {useEffect, useState} from 'react';
import AppBar from '@mui/material/AppBar';
import Button from '@mui/material/Button';
import CssBaseline from '@mui/material/CssBaseline';
import Toolbar from '@mui/material/Toolbar';
import Typography from '@mui/material/Typography';
import Link from '@mui/material/Link';
import GlobalStyles from '@mui/material/GlobalStyles';
import {Icon} from "semantic-ui-react";
import {Bucket} from 'model/Bucket';

import BucketService from "../../services/BucketService";
import {useNavigate} from "react-router-dom";
import {useSessionStore} from "../../store";

function PricingContent() {
    const [bucket, setBucket] = useState<Bucket>();
    const user = useSessionStore(state => state.user);
    const navigate = useNavigate();
    console.log(bucket);

    useEffect(() => {
        BucketService.findBucketByUserId(Number(user?.userId))
            .then(response => setBucket(response));
    }, []);
    return (
        <React.Fragment>
            <GlobalStyles styles={{ul: {margin: 0, padding: 0, listStyle: 'none'}}}/>
            <CssBaseline/>
            <AppBar
                position="static"
                color="default"
                elevation={0}
                sx={{borderBottom: (theme) => `1px solid ${theme.palette.divider}`}}
            >
                <Toolbar sx={{flexWrap: 'wrap'}}>
                    <Typography variant="h6" color="inherit" noWrap sx={{flexGrow: 1}}>
                        Tiny-Winy
                    </Typography>
                    <nav>
                        <Link
                            variant="button"
                            color="text.primary"
                            href="/gallery"
                            sx={{my: 1, mx: 1.5}}
                        >
                            Gallery
                        </Link>
                        <Link
                            variant="button"
                            color="text.primary"
                            href="/products/type/toys"
                            sx={{my: 1, mx: 1.5}}
                        >
                            Products
                        </Link>
                        <Link
                            variant="button"
                            color="text.primary"
                            href="/contacts"
                            sx={{my: 1, mx: 1.5}}
                        >
                            Contacts
                        </Link>
                        <Link
                            variant="button"
                            color="text.primary"
                            href="/reviews"
                            sx={{my: 1, mx: 1.5}}
                        >
                            Reviews
                        </Link>
                    </nav>
                    <Button
                        onClick={() => navigate(`/bucket/${bucket?.bucketId}`)}
                    >
                        <Icon name='shop' size='big' color='brown'/>
                    </Button>
                    <Button onClick={() => navigate(`/users/${user?.userId}`)}>
                        <Icon name='user' size='big' color='brown'/>
                    </Button>
                    <Button href="/" variant="outlined" sx={{my: 1, mx: 1.5}}>
                        <Icon name='log out' size='big' color='brown'/>
                    </Button>
                </Toolbar>
            </AppBar>
        </React.Fragment>
    );
}

export default function Pricing() {
    return <PricingContent/>;
}
