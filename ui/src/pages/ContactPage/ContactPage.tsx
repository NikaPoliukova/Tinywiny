import MyHeader from 'pages/component/MyHeader'
import React from 'react'
import {Button, Divider, Header, Icon, Table} from 'semantic-ui-react'


const Contacts = () => (
    <>
        <MyHeader />

        <img src="./image/3771518.png"/>

        <Table definition>
            <Table.Body>
                <Table.Row>
                    <Table.Cell width={2}>Contact number </Table.Cell>
                    <Table.Cell>+375-29-111-55-22</Table.Cell>
                </Table.Row>
                <Table.Row>
                    <Table.Cell>
                        My credentials</Table.Cell>
                    <Table.Cell>Ivanova Ekaterina Ivanovna</Table.Cell>
                </Table.Row>
                <Table.Row>
                    <Table.Cell>My email</Table.Cell>
                    <Table.Cell>tiny-winy@gmail.com</Table.Cell>
                </Table.Row>
            </Table.Body>
        </Table>

           <div>
            <Button color='facebook' >
                <Icon name='facebook' /> Facebook
            </Button>

            <Button color='instagram' href = "https://www.instagram.com/tinywinydecor/" >
                <Icon name='instagram' /> Instagram
            </Button>
            <Button color='youtube'>
                <Icon name='youtube' /> YouTube
            </Button>
        </div>
    </>
)

export default Contacts