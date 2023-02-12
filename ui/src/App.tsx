import React from 'react';
import {Route, Routes} from 'react-router-dom';
import UsersPage from './pages/UsersPage/UsersPage';
import HomePage from "./pages/HomePage/HomePage";
import {Registry} from "./pages/RegistrationPage/RegistryPage";
import {SignIn} from "./pages/LoginPage/LoginPage";
import ReviewsPage from "./pages/ReviewsPage/ReviewsPage";
import OrderPage from "./pages/OrderPage/OrderPage";
import BucketPage from "./pages/BucketPage/BucketPage";
import OrdersPage from "./pages/OrdersPage/OrdersPage";
import GalleryPage from "./pages/GalleryPage/GalleryPage";

import Products from "./pages/ProductsPage/ProductsPage";
import {UserPage} from "./pages/UserPage/UserPage";
import {MyOrdersPage} from "./pages/OrderPage/MyOrdersPage";
import {ProductPage} from "./pages/ProductPage/ProductPage";



function App() {
    return (
        <Routes>
            <Route path={'/bucket/:bucketId'} element={<BucketPage/>}/>
            <Route path={'/login'} element={<SignIn/>}/>
            <Route path={'/users'} element={<UsersPage/>}/>

            <Route path={'/users/:userId'} element={<UserPage/>}/>
            <Route path={'/orders/:orderId'} element={<MyOrdersPage/>}/>
            <Route path={'/'} element={<HomePage/>}/>
            <Route path={'/registration'} element={<Registry/>}/>
            <Route path={'/reviews'} element={<ReviewsPage/>}/>
            <Route path={'/orders/:orderId'} element={<OrderPage/>}/>
            <Route path={'/orders'} element={<OrdersPage/>}/>
            <Route path={'/products/:type'} element={<Products/>}/>
            <Route path={'/products/product/:id'} element={<ProductPage/>}/>
            <Route path={'/gallery'} element={<GalleryPage/>}/>

        </Routes>
    );
}

export default App;
