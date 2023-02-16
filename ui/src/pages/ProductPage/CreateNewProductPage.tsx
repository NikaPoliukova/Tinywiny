import React, {useState} from 'react'
import {Form, Header, Icon, Segment} from 'semantic-ui-react'
import {IconButton} from "@mui/material";
import {AddAPhoto} from "@mui/icons-material";
import {Product} from "../../model/Product";

const getTypeProduct = [
    {key: 'm', text: 'mobil', value: 'male'},
    {key: 'f', text: 'toy', value: 'female'},
    {key: 'o', text: 'Other', value: 'other'},
]

export function CreateProduct() {
    const [product, setProduct] = useState<Product>();

    const [productName, setProductName] = useState('')
    const [price, setPrice] = useState(0)
    const [countInStock, setCountInStock] = useState(0)
    const [description, setDescription] = useState('')
    const [idType, setIdType] = useState(0)
    const [file, setFile] = useState(null)
    /*  const addProduct = () => {
          setProduct([...product, {productName: '', price:0 ,countInStock: 0, description: '', idType: 0}])
      }*/
    return (
        <>
            <Segment placeholder>
                <Header icon>
                    <Icon name='photo'/>
                </Header>
                <IconButton color="primary" aria-label="upload picture" component="label">
                    <input hidden accept="image/*" type="file"/>
                    <AddAPhoto/>
                </IconButton>
            </Segment>
            <Form>
                <Form.Group widths='equal'>
                    <Form.Input fluid label='Product Name' placeholder='Product Name'/>
                </Form.Group>
                <Form.Group widths='equal'>
                    <Form.Select
                        fluid
                        label='Type product'
                        options={getTypeProduct}
                        placeholder='Type product'
                    />
                </Form.Group>
                <Form.Group  widths='equal'>
                    <Form.Input fluid label='Price' placeholder='Price'/>
                </Form.Group>
                <Form.Group widths='equal'>
                    <Form.Input fluid label='Count in stock' placeholder='Count in stock'/>
                </Form.Group>
                <Form.Group widths='equal'>
                </Form.Group>
                <Form.TextArea label='Discription' placeholder='Write dicscription...'/>

                <Form.Button>Add product</Form.Button>
            </Form>
        </>
    )
}


