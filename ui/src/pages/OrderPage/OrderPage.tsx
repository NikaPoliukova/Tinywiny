import React, {useEffect, useState} from 'react';

import {Card} from "@mui/material";

import {useParams} from "react-router-dom";
import OrderService from "../../services/OrderService";
import {Order} from "../../model/Order";
import {Footer} from "../component/Footer";
import MyHeader from 'pages/component/MyHeader';
import {Header, Icon, Table} from "semantic-ui-react";


export const OrderPage = () => {
    const {orderId} = useParams();
    const [order, setOrder] = useState<Order>();

    useEffect(() => {
        OrderService.findOrderByOrderId(Number(orderId))
            .then(response => setOrder(response));
    }, []);

    return (
        <React.Fragment>
            <MyHeader/>
            <Header as='h2' icon textAlign='center'>
                <Icon name='shopping bag' circular size='big'/>
                <Header.Content>Order details</Header.Content>
            </Header>

            <Card style={{width: 1000}}>
                <div>
                    <Table definition>
                        <Table.Body>
                            <Table.Row>
                                <Table.Cell width={2}>Number order</Table.Cell>
                                <Table.Cell>{order?.orderId}</Table.Cell>
                            </Table.Row>
                            <Table.Row>
                                <Table.Cell>Status Order</Table.Cell>
                                <Table.Cell>{order?.statusOrder}</Table.Cell>
                            </Table.Row>
                            <Table.Row>
                                <Table.Cell>Date created</Table.Cell>
                                <Table.Cell>{order?.createdAt}</Table.Cell>
                            </Table.Row>
                            <Table.Row>
                                <Table.Cell>Comment</Table.Cell>
                                <Table.Cell>{order?.commentOrder}</Table.Cell>
                            </Table.Row>
                        </Table.Body>
                    </Table>
                </div>
            </Card>
            <Footer/>
        </React.Fragment>
    );
}