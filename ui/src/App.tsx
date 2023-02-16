import React from 'react';
import {Route, Routes} from 'react-router-dom';
import UsersPage from './pages/UsersPage/UsersPage';
import HomePage from "./pages/HomePage/HomePage";
import {Registry} from "./pages/RegistrationPage/RegistryPage";
import {SignIn} from "./pages/LoginPage/LoginPage";
import ReviewsPage from "./pages/ReviewsPage/ReviewsPage";

import BucketPage from "./pages/BucketPage/BucketPage";
import OrdersPage from "./pages/OrdersPage/OrdersPage";
import GalleryPage from "./pages/GalleryPage/GalleryPage";

import Products from "./pages/ProductsPage/ProductsPage";
import {UserPage} from "./pages/UserPage/UserPage";

import {ProductPage} from "./pages/ProductPage/ProductPage";
import {OrderPage} from "./pages/OrderPage/OrderPage";
import CreateOrderPage from "./pages/OrderPage/CreateOrderPage/CreateOrderPage";
import MyOrdersPage from "./pages/OrderPage/MyOrdersPage";
import DividerExampleHorizontalTable from "./pages/ContactPage/ContactPage";
import {CreateProduct} from "./pages/ProductPage/CreateNewProductPage";
import {CreateReview} from "./pages/ReviewsPage/CreateReviewPage";
import {AdminPage} from "./pages/AdminPage/AdminPage";



function App() {
    return (
        <Routes>
            <Route path={'/'} element={<HomePage/>}/>
            <Route path={'/login'} element={<SignIn/>}/>
            <Route path={'/users'} element={<UsersPage/>}/>
            <Route path={'/users/:userId'} element={<UserPage/>}/>
            <Route path={'/registration'} element={<Registry/>}/>
            <Route path={'/reviews'} element={<ReviewsPage/>}/>
            <Route path={'/gallery'} element={<GalleryPage/>}/>
            <Route path={'/products/:type'} element={<Products/>}/>
            <Route path={'/products/product/:productId'} element={<ProductPage/>}/>
            <Route path={'/orders'} element={<OrdersPage/>}/>
            <Route path={'/bucket/:bucketId'} element={<BucketPage/>}/>


            <Route path={'/orders/:userId'} element={<MyOrdersPage/>}/>
            <Route path={'/orders/order/:orderId'} element={<OrderPage/>}/>
                <Route path={'/orders/create'} element={<CreateOrderPage/>}/>
            <Route path={'/products/create'} element={<CreateProduct/>}/>
            <Route path={'/contacts'} element={<DividerExampleHorizontalTable/>}/>
            <Route path={'/review/create'} element={<CreateReview/>}/>
            <Route path={'/admin'} element={<AdminPage/>}/>
        </Routes>
    );
}
const styleLink = document.createElement("link");
styleLink.rel = "stylesheet";
styleLink.href = "https://cdn.jsdelivr.net/npm/semantic-ui/dist/semantic.min.css";
document.head.appendChild(styleLink);
export default App;
