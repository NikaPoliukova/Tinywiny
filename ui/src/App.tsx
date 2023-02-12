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
import {Products} from "./pages/ProductsPage/ProductsPage";
import GalleryPage from "./pages/GalleryPage/GalleryPage";




function App() {
    return (
        <Routes>
            <Route path={'/login'} element={<SignIn/>}/>
            <Route path={'/users'} element={<UsersPage/>}/>
            <Route path={'/'} element={<HomePage/>}/>
            <Route path={'/registration'} element={<Registry/>}/>
            <Route path={'/reviews'} element={<ReviewsPage/>}/>
            <Route path={'/products-for-order'} element={<OrderPage/>}/>
            <Route path={'/orders'} element={<OrdersPage/>}/>
            <Route path={'/bucket'} element={<BucketPage/>}/>
            <Route path={'/products'} element={<Products/>}/>
            <Route path={'/gallery'} element={<GalleryPage/>}/>


        </Routes>
    );
}

export default App;
