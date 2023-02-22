import MyHeader from 'pages/component/MyHeader'
import React from 'react'
import {Button, Divider, Header, Icon, Table} from 'semantic-ui-react'
import ReviewForUsers from "../ReviewsPage/ReviewForUsers";

const Contacts = () => (
    <>
        <MyHeader />

        <Divider horizontal>
            <Header as='h4'>
                <Icon name='tag'/>
                Information
            </Header>
        </Divider>

        <Table definition>
            <Table.Body>
                <Table.Row>
                    <Table.Cell width={2}>Price</Table.Cell>
                    <Table.Cell>1" x 2"</Table.Cell>
                </Table.Row>
                <Table.Row>
                    <Table.Cell>Count in stock</Table.Cell>
                    <Table.Cell>6 ounces</Table.Cell>
                </Table.Row>
                <Table.Row>
                    <Table.Cell>Description</Table.Cell>
                    <Table.Cell>Yellowish</Table.Cell>
                </Table.Row>
            </Table.Body>
        </Table>
<h3> reviews</h3>
        <ReviewForUsers />
        <div>
            <Button color='facebook'>
                <Icon name='facebook' /> Facebook
            </Button>

            <Button color='instagram' >
                <Icon name='instagram' /> Instagram
            </Button>
            <Button color='youtube'>
                <Icon name='youtube' /> YouTube
            </Button>
        </div>
    </>
)

export default Contacts